(ns secret-santa-app.datasource.sqlutilities)

(defn join [token lst]
  (clojure.string/join token lst))

(def sql-types-mp 
  {
   :bigint "BIGINT"
   :string "VARCHAR(200)"
   :int "INT"
   :id "PRIMARY KEY BIGINT AUTO_GENERATED"
   })

(defn to-sql [entry]
  (let [column-name (name (entry 0))
        data-type (entry 1)]
    (join " "
          [column-name
           (sql-types-mp data-type)])))

(defn generate-column-sql [data]
  (join ", "
        (map 
         to-sql
         data)))

(defn entity-table-sql 
  ([entity] (entity-table-sql entity generate-column-sql))
  ([entity column-generator]
     (let [entity-name (first (keys entity))
           columns (entity entity-name)
           column-sql (column-generator columns)]
       (join " " 
             ["CREATE table" 
              (name entity-name)
              (str "("
                   column-sql
                   ")")]
             ))))
