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

package com.borachio.scalatest

import com.borachio.{AbstractMockFactory, MockingClassLoader}
import org.scalatest.{BeforeAndAfterEach, Distributor, Filter, Reporter, Stopper, Suite, Tracker}
import java.net.URLClassLoader

/** Trait that can be mixed into a [[http://www.scalatest.org/ ScalaTest]] suite to provide
  * mocking support.
  *
  * See [[com.borachio]] for overview documentation.
  */
trait MockFactory extends AbstractMockFactory with BeforeAndAfterEach { this: Suite =>
  
  override def beforeEach() {
    resetExpectations
  }
  
  override def afterEach() {
    if (autoVerify)
      verifyExpectations
  }
  
  protected abstract override def runTests(testName: Option[String], reporter: Reporter, stopper: Stopper, filter: Filter,
    configMap: Map[String, Any], distributor: Option[Distributor], tracker: Tracker) {
    
    val clazz = Class.forName(getClass.getName, true, new MockingClassLoader)
    val withMocks = clazz.newInstance
    val methods = clazz.getMethods
    val m = clazz.getMethod("runInternal", classOf[Option[String]], classOf[Reporter], classOf[Stopper],
      classOf[Filter], classOf[Map[String, Any]], classOf[Option[Distributor]], classOf[Tracker])
    m.invoke(withMocks, testName, reporter, stopper, filter, configMap, distributor, tracker)
  }
  
  def runInternal(testName: Option[String], reporter: Reporter, stopper: Stopper, filter: Filter,
    configMap: Map[String, Any], distributor: Option[Distributor], tracker: Tracker) {
    
    super.runTests(testName, reporter, stopper, filter, configMap, distributor, tracker)
  }
  
  protected var autoVerify = true
}