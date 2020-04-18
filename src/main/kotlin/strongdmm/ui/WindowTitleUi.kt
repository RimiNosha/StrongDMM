package strongdmm.ui

import org.lwjgl.glfw.GLFW.glfwSetWindowTitle
import strongdmm.StrongDMM
import strongdmm.byond.dme.Dme
import strongdmm.byond.dmm.Dmm
import strongdmm.event.Event
import strongdmm.event.EventConsumer
import strongdmm.event.type.Reaction
import strongdmm.window.AppWindow

class WindowTitleUi : EventConsumer {
    private var environmentName: String = ""

    init {
        consumeEvent(Reaction.SelectedMapChanged::class.java, ::handleSelectedMapChanged)
        consumeEvent(Reaction.SelectedMapClosed::class.java, ::handleSelectedMapClosed)
        consumeEvent(Reaction.EnvironmentChanged::class.java, ::handleEnvironmentChanged)
        consumeEvent(Reaction.EnvironmentReset::class.java, ::handleEnvironmentReset)
    }

    private fun handleSelectedMapChanged(event: Event<Dmm, Unit>) {
        glfwSetWindowTitle(AppWindow.window, "$environmentName [${event.body.mapPath.readable}] - ${StrongDMM.TITLE}")
    }

    private fun handleSelectedMapClosed() {
        glfwSetWindowTitle(AppWindow.window, StrongDMM.TITLE)
    }

    private fun handleEnvironmentChanged(event: Event<Dme, Unit>) {
        environmentName = event.body.name
        glfwSetWindowTitle(AppWindow.window, "$environmentName - ${StrongDMM.TITLE}")
    }

    private fun handleEnvironmentReset() {
        glfwSetWindowTitle(AppWindow.window, StrongDMM.TITLE)
    }
}