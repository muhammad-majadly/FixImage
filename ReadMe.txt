
-----------------------------------
Questions:

1. If there are m boundary pixels and n pixels inside the hole, what’s the complexity of the
algorithm that fills the hole, assuming that the hole and boundary were already found?
Try to also express the complexity only in terms of n .
n is the number of pixels in the hole. m is the number of pixels in the boundary ??

answer: 
 complexity: O(n*m) ~ O(n*sqrt(n))
 For each point found in a hole, we have to sum up all the points at the boundary.
 The shape area is approximately the square of its circumference so m ~O(sqrt(n)).


------------------------------------

2.Describe an algorithm that approximates the result in O(n)?

answer:
 algorithm: for each pixel in the hole, we use only the k-nearest neighbours of the pixel and not all boundary pixel, where k is a constant.
 
 Complexity: o(n) for each hole points we make at most o(k) Calculations.





