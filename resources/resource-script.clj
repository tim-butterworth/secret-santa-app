
(import java.io.File)
(import java.io.FileOutputStream)
(import java.io.OutputStreamWriter)
(import java.io.BufferedWriter)

(def js-root-path "/Users/timothybutterworth/clojure_projects/secret-santa-app/resources/public/javascript/app")
(def css-root-path "/Users/timothybutterworth/clojure_projects/secret-santa-app/resources/public/css")

(defn is-dir [file]
  (. file isDirectory))

(defn vec-join [container v]
  (reduce (fn [accume n]
            (conj accume n))
          container
          v))

(defn get-files [file]
  (map (fn [n] n) (. file listFiles)))

(defn gather-files 
  ([file]
   (gather-files [file] []))
  ([unverified files]
   (let [processed (reduce
                    (fn [accume n] 
                      (let [is-a-dir (is-dir n)
                            is-a-file (not is-a-dir)
                            unverified (:unverified accume)
                            all-unverified (if is-a-dir
                                             (vec-join unverified (get-files n))
                                             unverified)
                            files (:files accume)
                            all-files (if is-a-file (conj files n) files)]
                        {:unverified all-unverified
                         :files all-files}))
                    {:unverified []
                     :files files}
                    unverified)]
     (if (empty? (processed :unverified))
       (processed :files)
       (gather-files (:unverified processed)
                     (:files processed))))))

(def root
  (. (. (File. "./resources/public") getAbsolutePath) replace "/./" "/"))

(defn gather-files-from-path [path]
  (map (fn [file]
         (. (. file getPath) replace root "")) 
       (gather-files (File. path))))

(defn get-css-files []
  (gather-files-from-path css-root-path))

(defn get-js-files []
  (gather-files-from-path js-root-path))

(defn wrap-for-write [lst header name]
  (let [acc [header (str "(def " name " ")]]
    (conj 
     (reduce (fn [accume n] 
               (conj accume (str "\"" n "\"")))
             acc
             lst)
     ")")))

(def fos (FileOutputStream. "temp.txt"))
(def out (OutputStreamWriter. fos "UTF-8"))
(def new-line "\n")
(def js-header
  "(ns secret-santa-app.views.app-js) \n")
(def new-line "\n")

(defn write-file [path files]
  (let [fos (FileOutputStream. path)
        outwriter (OutputStreamWriter. fos "UTF-8")
        writer (BufferedWriter. outwriter)]
    (try 
      (doseq [x (wrap-for-write (get-js-files) js-header "js-files")] 
        (do
          (.write writer (str x new-line))))
      (finally (.close writer)))))


(defn write-file []
  (with-open [w (clojure.java.io/writer  "w.txt" :append false)]
    (. w write (str "hello" "world"))))
