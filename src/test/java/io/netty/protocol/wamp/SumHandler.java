package io.netty.protocol.wamp;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.node.IntNode;
import io.netty.protocol.wamp.server.CallErrorException;
import io.netty.protocol.wamp.server.RpcHandler;

import java.util.List;

public class SumHandler implements RpcHandler {
	@Override
	public TreeNode call(List<TreeNode> args, HandlerContext ctx) throws CallErrorException {
		int sum;
		sum = ((IntNode) args.get(0)).intValue() + ((IntNode) args.get(1)).intValue();
		return new IntNode(sum);
	}
}
