/*
 *  Copyright (C) 2010-2014 JPEXS, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library. */
package com.jpexs.decompiler.flash.abc.avm2.instructions.arithmetic;

import com.jpexs.decompiler.flash.abc.ABC;
import com.jpexs.decompiler.flash.abc.avm2.AVM2Code;
import com.jpexs.decompiler.flash.abc.avm2.AVM2ConstantPool;
import com.jpexs.decompiler.flash.abc.avm2.LocalDataArea;
import com.jpexs.decompiler.flash.abc.avm2.instructions.AVM2Instruction;
import com.jpexs.decompiler.flash.abc.avm2.instructions.InstructionDefinition;
import com.jpexs.decompiler.flash.abc.avm2.model.operations.AddAVM2Item;
import com.jpexs.decompiler.flash.abc.types.MethodBody;
import com.jpexs.decompiler.flash.abc.types.MethodInfo;
import com.jpexs.decompiler.graph.GraphTargetItem;
import com.jpexs.decompiler.graph.ScopeStack;
import com.jpexs.decompiler.graph.TranslateStack;
import java.util.HashMap;
import java.util.List;

public class AddIns extends InstructionDefinition {

    public AddIns() {
        super(0xa0, "add", new int[]{});
    }

    @Override
    public void execute(LocalDataArea lda, AVM2ConstantPool constants, List<Object> arguments) {
        Object o1 = lda.operandStack.pop();
        Object o2 = lda.operandStack.pop();
        if ((o1 instanceof Long) && ((o2 instanceof Long))) {
            Long ret = Long.valueOf(((Long) o1).longValue() + ((Long) o2).longValue());
            lda.operandStack.push(ret);
        } else if ((o1 instanceof Double) && ((o2 instanceof Double))) {
            Double ret = Double.valueOf(((Double) o1).doubleValue() + ((Double) o2).doubleValue());
            lda.operandStack.push(ret);
        } else if ((o1 instanceof Long) && ((o2 instanceof Double))) {
            Double ret = new Double(((Long) o1).longValue() + ((Double) o2).doubleValue());
            lda.operandStack.push(ret);
        } else if ((o1 instanceof Double) && ((o2 instanceof Long))) {
            Double ret = new Double(((Double) o1).doubleValue() + ((Long) o2).longValue());
            lda.operandStack.push(ret);
        } else {
            String s = o1.toString() + o2.toString();
            lda.operandStack.push(s);
        }
    }

    @Override
    public void translate(boolean isStatic, int scriptIndex, int classIndex, HashMap<Integer, GraphTargetItem> localRegs, TranslateStack stack, ScopeStack scopeStack, AVM2ConstantPool constants, AVM2Instruction ins, List<MethodInfo> method_info, List<GraphTargetItem> output, MethodBody body, ABC abc, HashMap<Integer, String> localRegNames, List<String> fullyQualifiedNames, String path, HashMap<Integer, Integer> localRegsAssignmentIps, int ip, HashMap<Integer, List<Integer>> refs, AVM2Code code) {
        GraphTargetItem v2 = stack.pop();
        GraphTargetItem v1 = stack.pop();
        stack.push(new AddAVM2Item(ins, v1, v2));
    }

    @Override
    public int getStackDelta(AVM2Instruction ins, ABC abc) {
        return -2 + 1;
    }
}
