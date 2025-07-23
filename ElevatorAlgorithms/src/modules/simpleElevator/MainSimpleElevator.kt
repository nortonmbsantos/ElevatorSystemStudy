import modules.simpleElevator.Elevator
import modules.simpleElevator.ElevatorController

fun main() {
        var elevator = Elevator()
        var elevatorController = ElevatorController(elevator)

        elevatorController.addToOrder(7)
        elevatorController.addToOrder(22)
        elevatorController.addToOrder(13)

        elevatorController.move() // indo pro 7
        elevatorController.addToOrder(2)
        elevatorController.move() // indo pro 13
        elevatorController.addToOrder(19)
        elevatorController.move() // indo pro 22
        elevatorController.move() // indo pro 19
        elevatorController.move() // indo pro 2


}