/* 
 * Copyright 2012 Phil Pratt-Szeliga and other contributors
 * http://chirrup.org/
 * 
 * See the file LICENSE for copying permission.
 */

package edu.syr.pcpratts.rootbeer.testcases.rootbeertest.kerneltemplate;

import edu.syr.pcpratts.rootbeer.runtime.Kernel;
import edu.syr.pcpratts.rootbeer.runtime.ThreadConfig;
import edu.syr.pcpratts.rootbeer.test.TestKernelTemplate;

public class FastMatrixTest implements TestKernelTemplate {

  int m_blockSize = 64;
  int m_gridSize = 64*14;
  public FastMatrixTest(){ 
    m_blockSize = 64;
    m_gridSize = 64*14;
  }

  public Kernel create() {
    int[] a = new int[m_blockSize*m_blockSize];
    int[] b = new int[m_blockSize*m_blockSize*m_gridSize];
    int[] c = new int[m_blockSize*m_blockSize*m_gridSize];

    for(int i = 0; i < a.length; ++i){
      a[i] = i;
    }

    for(int i = 0; i < b.length; ++i){
      b[i] = i;
    }
    Kernel ret = new MatrixKernel(a, b, c, m_blockSize, m_gridSize);
    return ret;
  }

  public ThreadConfig getThreadConfig() {
    ThreadConfig ret = new ThreadConfig(m_blockSize, m_gridSize);
    return ret;
  }

  public boolean compare(Kernel original, Kernel from_heap) {
    MatrixKernel lhs = (MatrixKernel) original;
    MatrixKernel rhs = (MatrixKernel) from_heap;
    return lhs.compare(rhs);
  }

}
