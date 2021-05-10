package com.person.kotlintest.homedialog

import android.util.SparseArray

class DialogActionFlow(private var flowNodes: SparseArray<DialogActionNode>) {

    private var callback: FlowCallback? = null

    private fun setFlowCallBack(flowCallback: FlowCallback?) {
        this.callback = flowCallback
    }

    /**
     * key只能是int
     * 会自己排序
     * 二分查找
     */
    fun start() {
        startWithNode(flowNodes.keyAt(0))
    }

    private fun startWithNode(nodeId: Int) {
        callback?.onFlowStart()
        val startIndex = flowNodes.indexOfKey(nodeId)
        if (startIndex == -1) {//找不到会返回-1
            callback?.onFlowFinish()
            return
        }
        val node = flowNodes.valueAt(startIndex)
        if (node != null) {
            callback?.onNodeChanged(node.getNodeId())
        } else {
            callback?.onFlowFinish()
            return
        }
        node.setActionCallback(object : DialogActionNode.ActionCallback {
            override fun onActionComplete() {
                findNextNode(startIndex)
            }
        })
    }

    private fun findNextNode(startIndex: Int) {
        val nextIndex = startIndex + 1
        val nextNode = flowNodes.valueAt(nextIndex)
        if (nextNode != null) {
            callback?.onNodeChanged(nextNode.getNodeId())
        } else {
            callback?.onFlowFinish()
            return
        }
        nextNode.setActionCallback(object : DialogActionNode.ActionCallback {
            override fun onActionComplete() {
                findNextNode(nextIndex)
            }
        })
    }

    interface FlowCallback {
        fun onFlowStart()
        fun onNodeChanged(nodeId: Int)
        fun onFlowFinish()
    }

    companion object {
        class Builder {
            private var flowNodes: SparseArray<DialogActionNode> = SparseArray()

            private var callback: FlowCallback? = null

            fun addNode(node: DialogActionNode): Builder {
                flowNodes.append(node.getNodeId(), node)
                return this
            }

            fun setFlowCallback(callback: FlowCallback): Builder {
                this.callback = callback
                return this
            }

            fun create(): DialogActionFlow {
                val flow = DialogActionFlow(flowNodes)
                flow.setFlowCallBack(callback)
                return flow
            }
        }
    }
}