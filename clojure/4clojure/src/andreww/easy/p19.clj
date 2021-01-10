(ns andreww.easy.p19)

(def __ (comp first reverse))

(and
  (= (__ [1 2 3 4 5]) 5)
  (= (__ '(5 4 3)) 3)
  (= (__ ["b" "c" "d"]) "d") )

