(ns codewars.longest
  (:require [clojure.string :as str]))

(defn longest [s1 s2]
  (->> (str s1 s2)
       set
       sort
       (apply str)))
