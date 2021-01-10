(ns andreww.medium.p65)

;;
;; This is longer than other solutions but I think it's clearer

(def __
  (fn [coll]
    (let [empty-type #(condp identical? %1 #{} :set {} :map [] :vector :list)
          either-map-or-set? #(= (count %1) (count (conj %1 (first %1))))
          vector-or-list-type #(if (= (last (conj %1 :__dummy_value__)) :__dummy_value__) :vector :list)
          map-or-set-type #(if (nil? (%1 (first %1))) :map :set)]
      (if (empty? coll) 
        (empty-type coll)
        (if (either-map-or-set? coll) 
          (map-or-set-type coll)
          (vector-or-list-type coll))))))

(and
  (= :map (__ {:a 1, :b 2}))
  (= :list (__ (range (rand-int 20))))
  (= :vector (__ [1 2 3 4 5 6]))
  (= :set (__ #{10 (rand-int 5)}))
  (= [:map :set :vector :list] (map __ [{} #{} [] ()])) )

; austintaylor
; (comp {\# :set \{ :map \[ :vector \c :list} first str)

; inecas
; #(cond
;   (get (conj % [:c 3]) :c) :map
;   (= (conj % 1) (conj % 1 1)) :set
;   (= [:one :two] (take 2 (conj % :two :one))) :list
;   (= [:one :two] (take 2 (reverse (conj % :two :one)))) :vector
;   )

; bradediger
; #(if (= :bar (:foo (into % {:foo :bar})))
;         :map
;         (let [onetwo (conj (conj % 1) 2)
;               twoone (conj (conj % 2) 1)]
;           (cond
;             (= onetwo twoone) :set
;             (= (take 2 onetwo) '(2 1)) :list
;             :else :vector)))
;
; vyzamyatin
; (fn [coll] 
;   (let [c (conj (empty coll) {1 2 3 4} {5 6})] 
;     (cond 
;       (= 3 (count c)) :map 
;       (empty? (flatten c)) :set 
;       (= {5 6} (first c)) :list 
;       :else :vector)))
; (identity (conj (empty #{:a :b}) {1 2 3 4} {5 6}))
