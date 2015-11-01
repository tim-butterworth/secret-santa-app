(ns secret-santa-app.datasource.sqlutilities)

(defn join [token lst]
  (clojure.string/join token lst))

(defmacro statement-setter [setter] 
  `(fn [st# i# n#] 
     (. st# ~(symbol setter) i# n#)))

(def sql-types-mp 
  {
   :bigint "BIGINT"
   :string "VARCHAR(200)"
   :int "INT"
   :id "BIGINT AUTO_INCREMENT PRIMARY KEY"
   })

(def set-sql-value-mp
  {
   :bigint (statement-setter "setLong")
   :string (statement-setter "setString")
   :int (statement-setter "setInt")
   })

(defn to-sql-type [entry]
  (let [column-name (name (entry 0))
        data-type (entry 1)]
    (join " "
          [column-name
           (sql-types-mp data-type)])))

(defn generate-column-sql [data]
  (join ", "
        (map 
         to-sql-type
         data)))

(defn entity-table-sql 
  ([entity] (entity-table-sql entity generate-column-sql))
  ([entity column-generator]
     (let [entity-name (first (keys entity))
           columns-with-types (entity entity-name)
           column-sql (column-generator columns-with-types)]
       (join " " 
             ["CREATE table" 
              (name entity-name)
              (str "("
                   column-sql
                   ")")]
             ))))

(defn remove-id-column [columns-with-types]
  (filter 
   (fn [entry]
     (not (= (entry 1) :id)))
   columns-with-types))

(defn save-entity-sql [entity-name entity-columns]
  (let [columns (map 
                 (fn [entry] (name (entry 0))) 
                 entity-columns)
        question-marks (map (fn [column] "?") columns)]
    (join " "
          ["INSERT into"
           (name entity-name)
           (str "(" (join ", " columns) ")")
           "values"
           (str "(" (join ", " question-marks) ")")])))

(defn set-statement-value [statement type value index]
  (let [type-setter (set-sql-value-mp type)]
    (type-setter statement index value)))

(defn entity-persistence-fn [entity]
  (let [entity-name (first (keys entity))
        entity-columns (remove-id-column (entity entity-name))
        sql (save-entity-sql entity-name entity-columns)]
    (fn [connection data]
      (let [prepared-statement (. connection prepareStatement sql)]
        (.
         (loop [columns entity-columns index 1]
           (if (empty? columns)
             prepared-statement
             (let [entry (first columns)
                   key (entry 0)
                   type (entry 1)]
               (do
                 (set-statement-value prepared-statement type (data key) index)
                 (recur (rest columns) (inc index))))))
         executeUpdate)))))
