(ns secret-santa-app.core-test
  (:require [clojure.test :refer :all]
            [secret-santa-app.views.htmlutils :refer :all]))

(def simplest
  (div))

(def full
  (div 
   {
    :content "content" 
    :attr {
           "hi" "there"
           }
    :vals ["some-val"]}))

(def vals-only
  (div 
   {
    :vals ["ng-something" "ng-something-else"]
    }))

(def content-only
  (div
   {
    :content "some cool content"
    }))

(def attr-only
  (div
   {
    :attr {"some" "attr"
           "another" "attr2"}
    }))

(deftest a-test
  (testing "htm generation"
    (is (=  full "<div hi='there' some-val>content</div>")))
  (testing "simplest"
    (is (= simplest "<div></div>")))
  (testing "vals-only"
    (is (= vals-only "<div ng-something ng-something-else></div>")))
  (testing "content-only"
    (is (= content-only "<div>some cool content</div>")))
  (testing "attr-only"
    (is (or
         (= attr-only "<div some='attr' another='attr2'></div>")
         (= attr-only "<div another='attr2' some='attr'></div>")))))
