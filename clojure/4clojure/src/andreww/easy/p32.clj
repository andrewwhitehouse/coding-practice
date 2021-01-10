(ns andreww.easy.p32)

; ex32
(def __ (partial mapcat #(list %1 %1)))

(and
  (= (__ [1 2 3]) '(1 1 2 2 3 3))
  (= (__ [:a :a :b :b]) '(:a :a :a :a :b :b :b :b))
  (= (__ [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4]))
  (= (__ [[1 2] [3 4]]) '([1 2] [1 2] [3 4] [3 4])))

; kohyama's solution
mapcat #(list % %)
;
; austintaylor's solution
#(interleave % %)
;
; nikkomega's solution
reduce #(conj %1 %2 %2) []
