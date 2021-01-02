package context

import "github.com/go-gl/glfw/v3.3/glfw"

func (c *Context) checkShouldClose() {
	if c.tmpShouldClose {
		glfw.GetCurrentContext().SetShouldClose(true)
	}
}
