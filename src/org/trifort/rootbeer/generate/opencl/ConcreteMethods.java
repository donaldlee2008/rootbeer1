package org.trifort.rootbeer.generate.opencl;

import java.util.ArrayList;
import java.util.List;

import soot.SootMethod;
import soot.rtaclassload.MethodSignature;
import soot.rtaclassload.MethodSignatureUtil;
import soot.rtaclassload.RTAClassLoader;

public class ConcreteMethods {

  public List<String> get(String signature){
    MethodSignatureUtil util = new MethodSignatureUtil();
    List<MethodSignature> virtual_methods = OpenCLScene.v().getVirtualMethods(signature);
    
    List<String> ret = new ArrayList<String>();
    for(MethodSignature virt_method : virtual_methods){
      util.parse(virt_method);
      SootMethod method = util.getSootMethod();
      if(method.isAbstract() == false){
        ret.add(virt_method.toString());
      }
    }
    
    return ret;
  }
}
