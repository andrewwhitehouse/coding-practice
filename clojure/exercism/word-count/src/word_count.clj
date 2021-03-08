(ns word-count
  (:require [clojure.string :as str]))

(defn word-count [s]
  (let [remove-blank #(remove str/blank? %)]
    (-> s
        (str/replace #"[!&@$%^,:]" "")
        (str/lower-case)
        (str/split #" ")
        remove-blank
        frequencies)))
