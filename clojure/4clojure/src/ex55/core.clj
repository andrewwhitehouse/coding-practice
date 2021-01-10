(ns ex55.core)

;(map (fn [[k v]] {k v}) {:a 1 :b 2})

(def __ 
  #(into {} (map (fn [[k v]] {k (count v)}) (group-by identity %)))
  )

(= (__ [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1})

(= (__ [:b :a :b :a :b]) {:a 2, :b 3})

(= (__ '([1 2] [1 3] [1 3])) {[1 2] 1, [1 3] 2})

; nikelandjelo's solution:
;(fn [s] (reduce #(assoc %1 %2 (inc (%1 %2 0))) {} s))
;(def a (assoc {} 1 (inc ({} 1 0))))
;(def b (assoc a 1 (inc (a 1 0))))
