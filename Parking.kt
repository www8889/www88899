package parking

class ParkingLot(var isNotParking:Boolean = true,var reg:String = "", var color:String = "", var spot:Int = -1)

fun main() {
	var cmds = mutableListOf<String>()
	var parkList =  mutableListOf<ParkingLot>()
	var parkingnumber = 0
	//println("park_number:$park_number \n"+isPark)
	while(true){
		cmds = readln().split(' ').toMutableList()
		//println(cmds)
		when(cmds[0]){
			"create" -> {
				val n = cmds[1].toInt()
				//if(n<0) continue
				parkList.clear()
				parkingnumber = 0
				var sp = 1
				repeat(n){
					val parkingLot = ParkingLot(spot=sp++)
					parkList.add(parkingLot)
				}
				println("Created a parking lot with ${parkList.size} spots.")
			}
			"status" -> {
				if(parkList.size == 0)
					println("Sorry, a parking lot has not been created.")
				else if(parkingnumber == 0)
					println("Parking lot is empty.")
				else{
					for(i in 0..parkList.size-1){
						if(!parkList[i].isNotParking){
							println("${i+1} ${parkList[i].reg} ${parkList[i].color}")                     
						}
					}
				}
			}
			"park" -> {
				if(parkList.size == 0)
					println("Sorry, a parking lot has not been created.")
				else if(parkingnumber == parkList.size){
					println("Sorry, the parking lot is full.")
				}else{
					for(i in 0..parkList.size-1){
						if(parkList[i].isNotParking){
							parkList[i].isNotParking = false
							parkList[i].reg = cmds[1]
							parkList[i].color = cmds[2]
							parkingnumber++
							println("${cmds[2]} car parked in spot ${i+1}.")
							break
						}
						//println("test i:$i. parkingnumber:$parkingnumber")
					}
				}
			}
			"leave" -> {
				val n = cmds[1].toInt()
				if(parkList.size == 0){
					println("Sorry, a parking lot has not been created.")
				}else{
					if(n < 1 || n > parkList.size){
						println("error leave")
					}else{
						if(!parkList[n-1].isNotParking){
							parkingnumber--
							parkList[n-1].isNotParking = true
							parkList[n-1].reg = ""
							parkList[n-1].color = ""
							parkList[n-1].spot = -1
							println("Spot $n is free.")
						}else println("No car in $n")
					}
				}
			}
			"exit" -> {
				break
			}
			"reg_by_color" -> {
				if(parkList.size == 0){
					println("Sorry, a parking lot has not been created.")
				}else{
					val color = cmds[1]
					var count = 0
					for(i in 0..parkList.size-1){
						if(!parkList[i].isNotParking && parkList[i].color.equals(color,true)){
							if(count==0)
								print("${parkList[i].reg}")
							else
								print(", ${parkList[i].reg}")
							count++
						}
					}
					if(count==0)
						print("No cars with color $color were found.")
					println("")
				}
			}
			"spot_by_color" -> {
				if(parkList.size == 0){
					println("Sorry, a parking lot has not been created.")
				}else{
					val color = cmds[1]
					var count = 0
					for(i in 0..parkList.size-1){
						if(!parkList[i].isNotParking && parkList[i].color.equals(color,true)){
							if(count==0)
								print("${parkList[i].spot}")
							else
								print(", ${parkList[i].spot}")
							count++
						}
					}
					if(count==0)
						print("No cars with color $color were found.")
					println("")
				}
			}
			"spot_by_reg" -> {
				if(parkList.size == 0){
					println("Sorry, a parking lot has not been created.")
				}else{
					val reg = cmds[1]
					var count = 0
					for(i in 0..parkList.size-1){
						if(!parkList[i].isNotParking && parkList[i].reg.equals(reg)){
							if(count==0)
								print("${parkList[i].spot}")
							else
								print(", ${parkList[i].spot}")
							count++
						}
					}
					if(count==0)
						print("No cars with registration number $reg were found.")
					println("")
				}
			}
			else -> println("error cmd")
		}
	}
}
