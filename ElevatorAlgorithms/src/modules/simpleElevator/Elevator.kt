package modules.simpleElevator

class Elevator {
    var elevatorState: ElevatorState
        get() {return elevatorState}
        set(value) {elevatorState = value}
    var floorsToGo = mutableListOf<Int>()
        get() {return floorsToGo}
        set(value) {field = value}
    var isDoorOpen: Boolean = false
    var currentFloor: Int = 0


    fun addToOrder(value: Int) {
        if (!floorsToGo.contains(value)){
            when(elevatorState) {
                ElevatorState.STOPED -> {
                    floorsToGo.add(value)
                }
                ElevatorState.MOVING_UP -> {
                    // If is the
                    // TODO Check is only going up
                    if(floorsToGo.first() > value && floorsToGo.last() < value){
                        floorsToGo.add(value)
                    }
                }
                ElevatorState.MOVING_DOWN -> {
                    if(floorsToGo.first() < value){
                        floorsToGo.add(value)
                    }
                }
            }
        }
    }

    fun stopAtFloor(value: Int) {

    }
}