package modules.simpleElevator

class ElevatorController(elevator: Elevator) {
    var elevator: Elevator = elevator

    fun addToOrder(value: Int) {
        if (value != elevator.currentFloor) {
            if (!elevator.floorsToGo.contains(value)) {

                when (elevator.elevatorState) {
                    ElevatorState.STOPED -> {
                        elevator.floorsToGo.add(value)

                        if (value > elevator.currentFloor) {
                            elevator.elevatorState = ElevatorState.MOVING_UP
                        } else if (value < elevator.currentFloor) {
                            elevator.elevatorState = ElevatorState.MOVING_DOWN
                        }

                    }

                    ElevatorState.MOVING_UP -> {
                        // If is the
                        // TODO Check is only going up
                        var indexToPlace = elevator.floorsToGo.size
                        //if higher than current floor and the last in order is lower, put in the middle
                        //if higher than current floor and the last in order is higher, put in order

                        // if is only going up
                        if (elevator.floorsToGo[0] < elevator.floorsToGo[elevator.floorsToGo.size - 1]) {

                            if (elevator.floorsToGo[0] < value && elevator.floorsToGo[elevator.floorsToGo.size - 1] > value) {
                                for ((index, floor) in elevator.floorsToGo.withIndex()) {
                                    if (floor > value) {
                                        indexToPlace = index
                                        break;
                                    }
                                }
                            }
                        }

                        // if is going up and then down
                        if (elevator.floorsToGo[0] > elevator.floorsToGo[elevator.floorsToGo.size - 1]) {
                            var splitIndex = 0
                            for ((index, floor) in elevator.floorsToGo.withIndex()) {
                                if (index >= 1){
                                    if (elevator.floorsToGo[index - 1] > floor) {
                                        splitIndex = index
                                    }
                                }
                            }

                            var pair = splitMutableListAtIndex(elevator.floorsToGo, splitIndex)
                            var firstPartArray = pair.first
                            var lastPartArray = pair.second

                            if (elevator.currentFloor < value && firstPartArray[firstPartArray.size - 1] > value) {
                                // add to first array
                                for ((index, floor) in firstPartArray.withIndex()) {
                                    if (floor > value) {
                                        indexToPlace = index
                                        break;
                                    }
                                }

                            } else {
                                // add to second array

                                for ((index, floor) in lastPartArray.withIndex()) {
                                    if (floor < value) {
                                        indexToPlace = index + firstPartArray.size - 1
                                        break;
                                    }
                                }
                            }
                            for ((index, floor) in elevator.floorsToGo.withIndex()) {

                                // if is higher or lower
                            }
                        }


                        elevator.floorsToGo.add(indexToPlace, value)
                    }

                    ElevatorState.MOVING_DOWN -> {
                        var indexToPlace = elevator.floorsToGo.size - 1
                        for ((index, floor) in elevator.floorsToGo.withIndex()) {
                            if (floor < value) {
                                indexToPlace = index
                                break;
                            }
                        }
                        elevator.floorsToGo.add(indexToPlace, value)
                    }
                }
            }
        }
    }


    fun move() {
        println("Current floor: " + elevator.currentFloor + " [ " + elevator.elevatorState + " ] to " + elevator.floorsToGo[0] + " in order: " + elevator.floorsToGo)

        if (elevator.floorsToGo.size > 1) {
            if (elevator.floorsToGo[1] > elevator.floorsToGo[0]) {
                elevator.elevatorState = ElevatorState.MOVING_UP
            } else if (elevator.floorsToGo[1] < elevator.floorsToGo[0]) {
                elevator.elevatorState = ElevatorState.MOVING_DOWN
            }
        } else {
            elevator.elevatorState = ElevatorState.STOPED
        }

        elevator.currentFloor = elevator.floorsToGo[0]
        elevator.floorsToGo.removeFirst()
    }


    fun <T> splitMutableListAtIndex(
        originalList: MutableList<T>,
        splitIndex: Int
    ): Pair<MutableList<T>, MutableList<T>> {
        require(splitIndex >= 0 && splitIndex <= originalList.size) {
            "Split index must be within the bounds of the list."
        }

        val firstPart = originalList.subList(0, splitIndex).toMutableList()
        val secondPart = originalList.subList(splitIndex, originalList.size).toMutableList()

        return Pair(firstPart, secondPart)
    }
}