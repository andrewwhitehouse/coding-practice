(ns andreww.medium.p50)

(def __ (fn [coll] (map val (group-by type coll))))

(and
  (= (set (__ [1 :a 2 :b 3 :c])) #{[1 2 3] [:a :b :c]})
  (= (set (__ [:a "foo"  "bar" :b])) #{[:a :b] ["foo" "bar"]})
  (= (set (__ [[1 2] :a [3 4] 5 6 :b])) #{[[1 2] [3 4]] [:a :b] [5 6]}) )


; kohyama
(def __ #(vals (group-by type %)))
