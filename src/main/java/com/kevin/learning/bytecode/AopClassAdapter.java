package com.kevin.learning.bytecode;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AopClassAdapter extends ClassVisitor implements Opcodes{

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);;
		if(name.startsWith("test")){
			mv = new AopMethodVisitor(this.api,mv);
		}
		return mv;
	}

	public AopClassAdapter(int arg0, ClassVisitor arg1) {
		super(arg0, arg1);
	}

	
}
