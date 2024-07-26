(ns murakami.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn is-markdown-file? [file]
  (str/ends-with? (.getName (io/file file)) ".md"))

(defn read-markdown-files [dir]
  (let [files (filter is-markdown-file? (file-seq (io/file dir)))]
    (doseq [file files]
      (println "Printing content of:" (.getName file))
      (println (slurp file)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
