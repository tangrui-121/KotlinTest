package com.person.kotlintest.homedialog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseArray
import com.person.ddd.LogUtils
import com.person.kotlintest.R
import com.person.kotlintest.download.UpdateActivity
import com.person.kotlintest.homedialog.dialogbase.Action
import com.person.kotlintest.homedialog.dialogbase.Node
import com.person.kotlintest.log
import com.person.kotlintest.singleClick
import com.person.kotlintest.toast
import kotlinx.android.synthetic.main.activity_home_dialog.*

/**
 * SparseArray + 责任链 处理首页弹窗过多
 */
class HomeDialogActivity : AppCompatActivity() {

    companion object {
        val Dialog_Key1 = 10
        val Dialog_Key2 = 20
        val Dialog_Key3 = 30
        val Dialog_Key4 = 40
    }

    val dialog1: DialogActionNode by lazy {
        val dialog1 = DialogActionNode(Dialog_Key1, object : Action {
            override fun doAction(node: Node) {
                errorDialog(title = "错误弹框", message = "我是第一个", cancel = {
                    "第一个弹窗--取消--下一个".toast()
                    node.onComplete()
                }, retry = {
                    "第一个弹窗--重试--下一个".toast()
                    node.onComplete()
                })
            }
        })
        dialog1
    }

    val dialog2: DialogActionNode by lazy {
        val dialog2 = DialogActionNode(Dialog_Key2, object : Action {
            override fun doAction(node: Node) {
                errorDialog(title = "错误弹框", message = "我是第二个", cancel = {
                    "第二个弹窗--取消--下一个".toast()
                    node.onComplete()
                }, retry = {
                    "第二个弹窗--重试--下一个".toast()
                    node.onComplete()
                })
            }
        })
        dialog2
    }

    val dialog3: DialogActionNode by lazy {
        val dialog3 = DialogActionNode(Dialog_Key3, object : Action {
            override fun doAction(node: Node) {
                errorDialog(title = "错误弹框", message = "我是第三个", cancel = {
                    "第三个弹窗--取消--下一个".toast()
                    node.onComplete()
                }, retry = {
                    "第三个弹窗--重试--下一个".toast()
                    node.onComplete()
                })
            }
        })
        dialog3
    }

    val dialog4: DialogActionNode by lazy {
        val dialog4 = DialogActionNode(Dialog_Key4, object : Action {
            override fun doAction(node: Node) {
                errorDialog(title = "错误弹框", message = "我是第四个") {
                    "第四个弹窗--重试--over".toast()
                    node.onComplete()
                }
            }
        })
        dialog4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_dialog)
        test_dialog.singleClick {
            DialogActionFlow.Companion.Builder().addNode(dialog1).addNode(dialog2).addNode(dialog3)
                .addNode(dialog4).setFlowCallback(object : DialogActionFlow.FlowCallback {
                    override fun onFlowStart() {
                        "DialogActionFlow开始执行".log()
                    }

                    override fun onNodeChanged(nodeId: Int) {
                        "执行了$nodeId".log()
                    }

                    override fun onFlowFinish() {
                        "弹框顺序执行完毕".log()
                    }
                }).create().start()
        }
        testSparseArray()
    }

    fun testSparseArray() {
        val aaa = SparseArray<String>()
        aaa.put(3, "dasd")
        aaa.put(6, "dasd222")
        aaa.put(2, "dasd333")
        aaa.put(8, "dasd444")
        aaa.put(9, "dasd555")

        val sss = aaa.keyAt(0)
        val ssss = aaa.valueAt(3)
        val sssss = aaa.indexOfKey(sss)

        "sss = $sss ssss = $ssss sssss = $sssss".log()
        //sss = 2 ssss = dasd444 sssss = 0
    }
}