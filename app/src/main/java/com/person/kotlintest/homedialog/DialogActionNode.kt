package com.person.kotlintest.homedialog

import com.person.kotlintest.base.Action
import com.person.kotlintest.base.Node

class DialogActionNode : Node {

    private var nodeId: Int
    private var action: Action

    private var callback: ActionCallback? = null

    constructor(nodeId: Int, action: Action) {
        this.nodeId = nodeId
        this.action = action
    }

    override fun getNodeId(): Int {
        return nodeId
    }

    override fun onComplete() {
        callback?.onActionComplete()
    }

    fun setActionCallback(actionCallback: ActionCallback) {
        this.callback = actionCallback
        //这里去真正的执行
        action.doAction(this)
    }

    interface ActionCallback {
        fun onActionComplete()
    }
}