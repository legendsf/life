import pytest


def hello(x):
    return x+1

def test_hello():
    assert hello(3)==5
