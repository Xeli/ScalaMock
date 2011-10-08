// Copyright (c) 2011 Paul Butcher
// 
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
// 
// The above copyright notice and this permission notice shall be included in
// all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// THE SOFTWARE.

package com.borachio

abstract class MockFunction(protected val factory: MockFactoryBase, name: Symbol) {

  protected def handle(arguments: Array[Any]) = factory.handle(this, arguments)
  
  private[borachio] def canHandle(that: MockFunction) = this == that
  
  private[borachio] val failIfUnexpected = true
}

abstract class TypeSafeMockFunction[R](factory: MockFactoryBase, name: Symbol) extends MockFunction(factory, name) {
  
  protected def makeExpectation() = new TypeSafeExpectation[R](this)

  protected def toExpectation() = {
    val expectation = makeExpectation
    factory.add(expectation)
    expectation
  }
  
  protected def setArguments(arguments: Any*) = {
    val expectation = toExpectation
    expectation.expects(arguments: _*)
    expectation
  }
  
  protected def setMatcher(matcher: Function1[Array[Any], Boolean]) = {
    val expectation = toExpectation
    expectation.argumentsMatcher = matcher
    expectation
  }
}

class MockFunction0[R](factory: MockFactoryBase, name: Symbol)  
  extends TypeSafeMockFunction[R](factory, name) with Function0[R] {

  override def toString = name.name.toString

  def apply() = handle(Array()).asInstanceOf[R]
  
  def expects() = setArguments()
}

class MockFunction1[T1, R](factory: MockFactoryBase, name: Symbol) 
  extends TypeSafeMockFunction[R](factory, name) with Function1[T1, R] {

  override def toString = name.name.toString

  def apply(v1: T1) = handle(Array(v1)).asInstanceOf[R]
  
  def expects(v1: MockParameter[T1]) = setArguments(v1.value)
  
  def expectsWhere(matcher: T1 => Boolean) = setMatcher(new FunctionAdapter1(matcher))
}

class MockFunction2[T1, T2, R](factory: MockFactoryBase, name: Symbol) 
  extends TypeSafeMockFunction[R](factory, name) with Function2[T1, T2, R] {
  
  override def toString = name.name.toString

  def apply(v1: T1, v2: T2) = handle(Array(v1, v2)).asInstanceOf[R]
  
  def expects(v1: MockParameter[T1], v2: MockParameter[T2]) = setArguments(v1.value, v2.value)
  
  def expectsWhere(matcher: (T1, T2) => Boolean) = setMatcher(new FunctionAdapter2(matcher))
}

class MockFunction3[T1, T2, T3, R](factory: MockFactoryBase, name: Symbol) 
  extends TypeSafeMockFunction[R](factory, name) with Function3[T1, T2, T3, R] {
  
  override def toString = name.name.toString

  def apply(v1: T1, v2: T2, v3: T3) = handle(Array(v1, v2, v3)).asInstanceOf[R]
  
  def expects(v1: MockParameter[T1], v2: MockParameter[T2], v3: MockParameter[T3]) = setArguments(v1.value, v2.value, v3.value)
  
  def expectsWhere(matcher: (T1, T2, T3) => Boolean) = setMatcher(new FunctionAdapter3(matcher))
}

class MockFunction4[T1, T2, T3, T4, R](factory: MockFactoryBase, name: Symbol) 
  extends TypeSafeMockFunction[R](factory, name) with Function4[T1, T2, T3, T4, R] {
  
  override def toString = name.name.toString

  def apply(v1: T1, v2: T2, v3: T3, v4: T4) = handle(Array(v1, v2, v3, v4)).asInstanceOf[R]
  
  def expects(v1: MockParameter[T1], v2: MockParameter[T2], v3: MockParameter[T3], v4: MockParameter[T4]) = setArguments(v1.value, v2.value, v3.value, v4.value)
  
  def expectsWhere(matcher: (T1, T2, T3, T4) => Boolean) = setMatcher(new FunctionAdapter4(matcher))
}

class MockFunction5[T1, T2, T3, T4, T5, R](factory: MockFactoryBase, name: Symbol) 
  extends TypeSafeMockFunction[R](factory, name) with Function5[T1, T2, T3, T4, T5, R] {
  
  override def toString = name.name.toString

  def apply(v1: T1, v2: T2, v3: T3, v4: T4, v5: T5) = handle(Array(v1, v2, v3, v4, v5)).asInstanceOf[R]

  def expects(v1: MockParameter[T1], v2: MockParameter[T2], v3: MockParameter[T3], v4: MockParameter[T4], v5: MockParameter[T5]) = setArguments(v1.value, v2.value, v3.value, v4.value, v5.value)
  
  def expectsWhere(matcher: (T1, T2, T3, T4, T5) => Boolean) = setMatcher(new FunctionAdapter5(matcher))
}

class MockFunction6[T1, T2, T3, T4, T5, T6, R](factory: MockFactoryBase, name: Symbol) 
  extends TypeSafeMockFunction[R](factory, name) with Function6[T1, T2, T3, T4, T5, T6, R] {
  
  override def toString = name.name.toString

  def apply(v1: T1, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6) = handle(Array(v1, v2, v3, v4, v5, v6)).asInstanceOf[R]

  def expects(v1: MockParameter[T1], v2: MockParameter[T2], v3: MockParameter[T3], v4: MockParameter[T4], v5: MockParameter[T5], v6: MockParameter[T6]) = setArguments(v1.value, v2.value, v3.value, v4.value, v5.value, v6.value)
  
  def expectsWhere(matcher: (T1, T2, T3, T4, T5, T6) => Boolean) = setMatcher(new FunctionAdapter6(matcher))
}

class MockFunction7[T1, T2, T3, T4, T5, T6, T7, R](factory: MockFactoryBase, name: Symbol) 
  extends TypeSafeMockFunction[R](factory, name) with Function7[T1, T2, T3, T4, T5, T6, T7, R] {
  
  override def toString = name.name.toString

  def apply(v1: T1, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7) = handle(Array(v1, v2, v3, v4, v5, v6, v7)).asInstanceOf[R]

  def expects(v1: MockParameter[T1], v2: MockParameter[T2], v3: MockParameter[T3], v4: MockParameter[T4], v5: MockParameter[T5], v6: MockParameter[T6], v7: MockParameter[T7]) = setArguments(v1.value, v2.value, v3.value, v4.value, v5.value, v6.value, v7.value)
  
  def expectsWhere(matcher: (T1, T2, T3, T4, T5, T6, T7) => Boolean) = setMatcher(new FunctionAdapter7(matcher))
}

class MockFunction8[T1, T2, T3, T4, T5, T6, T7, T8, R](factory: MockFactoryBase, name: Symbol) 
  extends TypeSafeMockFunction[R](factory, name) with Function8[T1, T2, T3, T4, T5, T6, T7, T8, R] {
  
  override def toString = name.name.toString

  def apply(v1: T1, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8) = handle(Array(v1, v2, v3, v4, v5, v6, v7, v8)).asInstanceOf[R]

  def expects(v1: MockParameter[T1], v2: MockParameter[T2], v3: MockParameter[T3], v4: MockParameter[T4], v5: MockParameter[T5], v6: MockParameter[T6], v7: MockParameter[T7], v8: MockParameter[T8]) = setArguments(v1.value, v2.value, v3.value, v4.value, v5.value, v6.value, v7.value, v8.value)
  
  def expectsWhere(matcher: (T1, T2, T3, T4, T5, T6, T7, T8) => Boolean) = setMatcher(new FunctionAdapter8(matcher))
}

class MockFunction9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R](factory: MockFactoryBase, name: Symbol) 
  extends TypeSafeMockFunction[R](factory, name) with Function9[T1, T2, T3, T4, T5, T6, T7, T8, T9, R] {
  
  override def toString = name.name.toString

  def apply(v1: T1, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9) = handle(Array(v1, v2, v3, v4, v5, v6, v7, v8, v9)).asInstanceOf[R]

  def expects(v1: MockParameter[T1], v2: MockParameter[T2], v3: MockParameter[T3], v4: MockParameter[T4], v5: MockParameter[T5], v6: MockParameter[T6], v7: MockParameter[T7], v8: MockParameter[T8], v9: MockParameter[T9]) = setArguments(v1.value, v2.value, v3.value, v4.value, v5.value, v6.value, v7.value, v8.value, v9.value)
  
  def expectsWhere(matcher: (T1, T2, T3, T4, T5, T6, T7, T8, T9) => Boolean) = setMatcher(new FunctionAdapter9(matcher))
}

class MockFunction10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R](factory: MockFactoryBase, name: Symbol) 
  extends TypeSafeMockFunction[R](factory, name) with Function10[T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R] {
  
  override def toString = name.name.toString

  def apply(v1: T1, v2: T2, v3: T3, v4: T4, v5: T5, v6: T6, v7: T7, v8: T8, v9: T9, v10: T10) = handle(Array(v1, v2, v3, v4, v5, v6, v7, v8, v9, v10)).asInstanceOf[R]

  def expects(v1: MockParameter[T1], v2: MockParameter[T2], v3: MockParameter[T3], v4: MockParameter[T4], v5: MockParameter[T5], v6: MockParameter[T6], v7: MockParameter[T7], v8: MockParameter[T8], v9: MockParameter[T9], v10: MockParameter[T10]) = setArguments(v1.value, v2.value, v3.value, v4.value, v5.value, v6.value, v7.value, v8.value, v9.value, v10.value)
  
  def expectsWhere(matcher: (T1, T2, T3, T4, T5, T6, T7, T8, T9, T10) => Boolean) = setMatcher(new FunctionAdapter10(matcher))
}
