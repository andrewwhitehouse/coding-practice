(ns andreww.medium.p44)


(def __ (fn [n coll] 
          (let [len (count coll) 
                order-f (if (< n 0) reverse identity)
                abs-n (Math/abs n)]
            (order-f (take len (drop abs-n (cycle (order-f coll))))))))

(and
  (= (__ 2 [1 2 3 4 5]) '(3 4 5 1 2))
  (= (__ -2 [1 2 3 4 5]) '(4 5 1 2 3))
  (= (__ 6 [1 2 3 4 5]) '(2 3 4 5 1))
  (= (__ 1 '(:a :b :c)) '(:b :c :a))
  (= (__ -4 '(:a :b :c)) '(:c :a :b)) )




; austintaylor
(fn [n s]
  (let [[a b] (split-at (mod n (count s)) s)]
    (concat b a)))
;
; kohyama
#(let [n (count %2)] (take n (drop (mod % n) (cycle %2))))

; jimm
(fn [rot-by coll]
  (let [len (count coll)
        inf (flatten (repeat coll))]
    (take len
          (if (pos? rot-by) (drop rot-by inf)
            (drop (mod rot-by len) inf)))))

