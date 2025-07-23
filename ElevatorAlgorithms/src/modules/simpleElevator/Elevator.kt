package modules.simpleElevator

class Elevator {
    var elevatorState: ElevatorState = ElevatorState.STOPED
    var floorsToGo = mutableListOf<Int>()
    var isDoorOpen: Boolean = false
    var currentFloor: Int = 0




}