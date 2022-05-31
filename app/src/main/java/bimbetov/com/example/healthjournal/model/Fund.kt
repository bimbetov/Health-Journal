package bimbetov.com.example.healthjournal.model

class Fund (
    private val id: Int = -1,
    private val city: String = "",
    private val fundName: String = "",
    private val fundImg: String = "",
    val imageList: List<String> = emptyList(),
    //private val needsImg: String = "",
    private val titleProblem: String = "",
    private var collectedAmount: Int = -1,
    private val needAmount: Int = -1,
    private val descriptionProblem: String = "",
    private val collectionAvailability: String = ""
) {
        fun getCollectionAvailability(): String {
            return collectionAvailability
        }

        fun getId(): Int {
            return id
        }

        fun getFundName(): String {
            return fundName
        }

        fun getFundImg(): String {
            return fundImg
        }

        fun getCity(): String {
            return city
        }

        fun getTitleProblem(): String {
            return titleProblem
        }

        fun getNeedAmount(): Int {
            return needAmount
        }

        fun getCollectedAmount(): Int {
            return collectedAmount
        }

        fun getDescriptionProblem(): String {
            return descriptionProblem
        }

        /*fun getNeedsImg(): String {
            return needsImg
        }*/
        fun getImgList(): List<String> {
            return imageList
        }
    }