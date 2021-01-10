(ns andreww.elementary.p134)

(def __ (fn [k m] (nil? (get m k 0))))

(and
  (true?  (__ :a {:a nil :b 2}))
  (false? (__ :b {:a nil :b 2}))
  (false? (__ :c {:a nil :b 2})) )

; N.B. (false? nil) is false
;
; austintaylor ...
(fn [k m] (and (nil? (k m)) (contains? m k)))
; nikelandjelo
#(nil? (%1 %2 0))

