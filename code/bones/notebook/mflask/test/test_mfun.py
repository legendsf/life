import unittest
import pytest
from mfun import *

class Testmfun(unittest.TestCase):
    def test_add(self):
       self.assertEqual(3,add(1,2))

    def test_add1(self):
        self.assertEqual(4,add(1,2))


def func(x):
    return x+1

def test_answer():
    assert func(3)==5

if __name__ == '__main__':
    unittest.main()