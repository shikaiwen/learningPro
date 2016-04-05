package com.kevin.learning.bytecode;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AopMethodVisitor extends MethodVisitor implements Opcodes{

	public AopMethodVisitor(int api, MethodVisitor mv) {
		super(api, mv);
	}
	
	
	@Override
	public void visitCode() {
		super.visitCode();
//		this.visitMethodInsn(opcode, owner, name, desc);
		this.visitMethodInsn(INVOKESTATIC, "com/kevin/learning/bytecode/AopInterceptor", "before", "()V", false);
	}

	@Override
	public void visitInsn(int opcode) {
		if (opcode >= IRETURN && opcode <= RETURN)
			this.visitMethodInsn(INVOKESTATIC, "com/kevin/learning/bytecode/AopInterceptor", "after", "()V", false);
		super.visitInsn(opcode);
	}



	
	
}
