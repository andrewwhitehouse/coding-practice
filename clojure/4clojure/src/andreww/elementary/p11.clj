(ns andreww.elementary.p11)

; ex11
(def __ [:b 2])

(= {:a 1, :b 2, :c 3} (conj {:a 1} __ [:c 3]))
