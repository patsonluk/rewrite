/*
 * Copyright 2013 <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ocpsoft.rewrite.config;

import org.ocpsoft.rewrite.config.DefaultOperationBuilder.DefaultCompositeOperation;
import org.ocpsoft.rewrite.context.EvaluationContext;
import org.ocpsoft.rewrite.event.InboundRewrite;
import org.ocpsoft.rewrite.event.OutboundRewrite;
import org.ocpsoft.rewrite.event.Rewrite;

/**
 * Utility for creating and wrapping {@link Operation} instances.
 * 
 * @author <a href="mailto:lincolnbaxter@gmail.com">Lincoln Baxter, III</a>
 */
public class Operations
{
   /**
    * Return a new {@link DefaultOperationBuilder} that takes no action when
    * {@link #perform(Rewrite, EvaluationContext)} is invoked.
    */
   public static OperationBuilder create()
   {
      return new NoOp();
   }

   /**
    * Wrap the given {@link Operation} in a new {@link InboundOperation} which will invoke the wrapped operation only
    * for inbound rewrites.
    */
   public static Operation onInbound(final Operation operation)
   {
      return new InboundOperation() {
         @Override
         public void performInbound(InboundRewrite event, EvaluationContext context)
         {
            operation.perform(event, context);
         }
      };
   }

   /**
    * Wrap the given {@link Operation} in a new {@link OutboundOperation} which will invoke the wrapped operation only
    * for outbound rewrites.
    */
   public static Operation onOutbound(final Operation operation)
   {
      return new OutboundOperation() {
         @Override
         public void performOutbound(OutboundRewrite event, EvaluationContext context)
         {
            operation.perform(event, context);
         }
      };
   }

   /**
    * Wrap a given {@link Operation} as a new {@link DefaultOperationBuilder} that performs the action of the original
    * {@link Operation} when {@link #perform(Rewrite, EvaluationContext)} is invoked.
    */
   public static OperationBuilder wrap(Operation operation)
   {
      if (operation == null)
         return create();
      if (operation instanceof OperationBuilder)
         return (OperationBuilder) operation;
      return new DefaultCompositeOperation(create(), operation);
   }

   public static class NoOp extends DefaultOperationBuilder
   {
      @Override
      public void perform(Rewrite event, EvaluationContext context)
      {}

      @Override
      public String toString()
      {
         return "NoOp";
      }
   }
}
