(ns andreww.medium.p46)


(def __ (fn [f] (fn [a b] (f b a))))

(and
  (= 3 ((__ nth) 2 [1 2 3 4 5]))
  (= true ((__ >) 7 8))
  (= 4 ((__ quot) 2 8))
  (= [1 2 3] ((__ take) [1 2 3 4 5] 3)) )

