package hellooutwatch

import scala.scalajs.js.JSApp
import outwatch.dom._
import outwatch.util.Store
import rxscalajs.Observable

sealed  trait  Action
case class AddTodo(todo: String) extends Action
case class RemoveTodo(todo: String) extends  Action

object Hellooutwatch extends JSApp {

  val initialState = List[String]()

  def reducer(currentState: List[String], action: Action) = action match {
    case AddTodo(todo) => currentState :+ todo
    case RemoveTodo(todo) => currentState.filter(_ != todo)
  }

  val store = Store(initialState, reducer)

  def todoComponent(todo: String) = {
    li(
      span(todo),
      button(click(RemoveTodo(todo)) --> store, "Delete")
    )
  }

  def textFieldComponent() = {
    val inputValues = createStringHandler()

    val disabledValues = inputValues
      .map(_.length < 4)
      .startWith(true)

    val submissions = createHandler[String]

    val addActions = submissions
      .map(todo => AddTodo(todo))

    store <-- addActions

    div(
      label("Todo:"),
      input(inputString --> inputValues),
      button(
        click(inputValues) --> submissions,
        disabled <-- disabledValues,
        "Submit"
      )
    )

  }

  def main(): Unit = {
    val listViews = store
        .map(_.map(todo => todoComponent(todo)))

    val root = div(
      textFieldComponent,
      ul(children <-- listViews)
    )

    OutWatch.render("#app", root)
  }
}
