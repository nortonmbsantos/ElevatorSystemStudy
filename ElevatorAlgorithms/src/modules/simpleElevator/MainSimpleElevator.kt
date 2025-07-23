import modules.simpleElevator.Elevator
import modules.simpleElevator.ElevatorController

fun main() {
        var elevator = Elevator()
        var elevatorController = ElevatorController(elevator)

        elevatorController.addToOrder(7)
        elevatorController.addToOrder(22)
        elevatorController.addToOrder(13)

        elevatorController.move()
        elevatorController.addToOrder(2)
        elevatorController.move()
        elevatorController.addToOrder(19)
        elevatorController.addToOrder(4)
        elevatorController.addToOrder(15)
        elevatorController.move()
        elevatorController.move()
        elevatorController.move()
        elevatorController.move()
        elevatorController.move()
        elevatorController.move()


}