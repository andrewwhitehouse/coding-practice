(ns andreww.easy.p30)


(def __
  (fn [coll]
    (reduce 
      (fn [a b] 
        (if 
          (or (empty? a) (not= b (last a))) 
          (conj a b) 
          a)) 
      []
      coll)))

(and
  (= (apply str (__ "Leeeeeerrroyyy")) "Leroy")
  (= (__ [1 1 2 3 3 2 2 3]) '(1 2 3 2 3))
  (= (__ [[1 2] [1 2] [3 4] [1 2]]) '([1 2] [3 4] [1 2])))



; simplest from other solutions ...
(def __ #(map first (partition-by identity %)))
