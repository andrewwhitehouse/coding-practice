(ns andreww.hard.p113)

(def __ 
  (fn [& args]
    (reify
      clojure.lang.Seqable 
      (seq [this] 
        (and (first args) (distinct args)))

      Object
      (toString [this]
        (clojure.string/join ", " (sort args))))))


(and 
  (= "1, 2, 3" (str (__ 2 1 3)))
  (= '(2 1 3) (seq (__ 2 1 3)))
  (= '(2 1 3) (seq (__ 2 1 3 3 1 2)))
  (= '(1) (seq (apply __ (repeat 5 1)))) 
  (= "1, 1, 1, 1, 1" (str (apply __ (repeat 5 1))))
  (and (= nil (seq (__)))
       (=  "" (str (__)))) ) 

; Other solutions ...
; kohyama
(fn [& s]
  (reify clojure.lang.Seqable
    (toString [_]
      (apply str (interpose ", " (sort s))))
    (seq [_] (seq (distinct s)))))
