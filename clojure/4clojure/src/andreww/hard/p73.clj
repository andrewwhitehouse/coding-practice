(ns andreww.hard.p73)

(def __
  (fn [rows]
    (->> 
      (concat
        rows
        (map
          (fn [idxs] (map #(nth %1 %2) rows idxs))
          [[0 0 0] [1 1 1] [2 2 2] [0 1 2] [2 1 0]]))
      (some #(and (apply = %1) 
                  (not= :e (first %1)) 
                  %1))
      (first)))  )

(and
  (= nil (__ [[:e :e :e] [:e :e :e] [:e :e :e]]))
  (= :x (__ [[:x :e :o] [:x :e :e] [:x :e :o]]))
  (= :o (__ [[:e :x :e] [:o :o :o] [:x :e :x]]))
  (= nil (__ [[:x :e :o] [:x :x :e] [:o :x :o]]))
  (= :x (__ [[:x :e :e] [:o :x :e] [:o :e :x]]))
  (= :o (__ [[:x :e :o] [:x :o :e] [:o :e :x]]))
  (= nil (__ [[:x :o :x] [:x :o :x] [:o :x :o]])))
