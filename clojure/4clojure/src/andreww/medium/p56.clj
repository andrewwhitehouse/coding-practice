(ns andreww.medium.p56)


(def __ (fn [coll] (reduce #(if (some #{%2} %1) %1 (conj %1 %2)) [] coll)))

(and
  (= (__ [1 2 1 3 1 2 4]) [1 2 3 4])
  (= (__ [:a :a :b :b :c :c]) [:a :b :c])
  (= (__ '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3]))
  (= (__ (range 50)) (range 50)))


; austintaylor's solution ...
; (fn [s]
;   (reduce #(if ((set %1) %2) %1 (conj %1 %2)) [] s))
