package com.dorbom.compose_study.profile

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.dorbom.compose_study.model.Message
import com.dorbom.compose_study.model.Picture

class ProfileScreenViewModel : ViewModel() {

    val footballPictures by lazy {
        mutableStateListOf<Picture>().apply {
            addAll(getFootballPictures())
        }
    }

    val baseballPictures by lazy {
        mutableStateListOf<Picture>().apply {
            addAll(getBaseballPictures())
        }
    }

    private fun getFootballPictures(): List<Picture> {
        return mutableListOf(
            Picture("https://i.namu.wiki/i/UAHp_lrjF5uVrVYQZtazyG4u1rAY0hu7FiBPJczYvH-csg2on7F6ZfDECLHaYiKIgossk_FcrH9Z8NdkgwsAtw.webp"),
            Picture("https://img.olympics.com/images/image/private/t_s_pog_staticContent_hero_xl_2x/f_auto/primary/wwmtwq6rgoqrwbi9b0qp"),
            Picture("https://flexible.img.hani.co.kr/flexible/normal/970/620/imgdb/original/2022/0930/20220930502822.jpg"),
            Picture("https://img.olympics.com/images/image/private/t_16-9_640/f_auto/v1538355600/primary/rpbbxc7e7b3xsh0xw2in"),
            Picture("https://image.ytn.co.kr/general/jpg/2019/0616/201906161015070475_d.jpg"),
            Picture("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQPyZdxCpKKoGaQtxJ408cyZMgNDFtpVZgPoA&usqp=CAU"),
            Picture("https://d2k5miyk6y5zf0.cloudfront.net/article/MYH/20230325/MYH20230325007600641.jpg"),
            Picture("https://siri.or.kr/wp/wp-content/uploads/2021/08/AHEN.png"),
            Picture("https://newsimg-hams.hankookilbo.com/2023/08/09/3813c81e-5bb2-4d81-9750-e20449a4647e.jpg"),
            Picture("https://i.namu.wiki/i/OoSwBESowsst2b3zVdnKRcAxB-pseoGu2-BsVFioNmGRHN0PVu3yVQymecVEne9kDInlf7Vp9iVcXpn01JCu4Q.webp"),
            Picture("https://www.kukinews.com/data/kuk/image/2022/08/04/kuk202208040265.680x.0.jpg"),
            Picture("https://images.unsplash.com/photo-1517466787929-bc90951d0974?q=80&w=1000&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D"),
            Picture("https://www.idaegu.co.kr/news/photo/201908/2019080801000213500013711.jpg"),
            Picture("https://upload.wikimedia.org/wikipedia/commons/thumb/4/42/Football_in_Bloomington%2C_Indiana%2C_1995.jpg/1200px-Football_in_Bloomington%2C_Indiana%2C_1995.jpg"),
            Picture("https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2023%2F10%2Fea-sports-fc-tactical-turn-based-strategy-game-announcement-1.jpg?cbr=1&q=90"),
            Picture("https://cdn.getnews.co.kr/news/photo/202211/607680_308845_5954.jpg"),
            Picture("https://cdn.footballist.co.kr/news/photo/202202/144104_70623_3454.jpg")
        )
    }

    private fun getBaseballPictures(): List<Picture> {
        return mutableListOf(
            Picture("https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2023%2F04%2Fthe-best-nba-player-poll-by-the-athletic-2023-1.jpg?cbr=1&q=90"),
            Picture("https://cdn.mhnse.com/news/photo/202212/160922_156078_733.jpg"),
            Picture("https://img.seoul.co.kr/img/upload/2023/02/08/SSC_20230208173707.jpg"),
            Picture("https://media.npr.org/assets/img/2023/04/01/ap23089090925050_custom-019741384a40a07d3c360b670a8e273e38fe3ce8-s1100-c50.jpg"),
            Picture("https://static01.nyt.com/images/2023/10/28/multimedia/00nba-rights-1-zlct/00nba-rights-1-zlct-mediumSquareAt3X.jpg"),
            Picture("https://www.sportspromedia.com/wp-content/uploads/2022/07/Steph-Curry-1.jpg"),
            Picture("https://imageio.forbes.com/specials-images/imageserve/659712cd0e3c98d500844563/Brooklyn-Nets-v-Atlanta-Hawks/0x0.jpg?crop=2962,1668,x0,y170,safe&height=400&width=711&fit=bounds"),
            Picture("https://cdn.nba.com/manage/2022/08/nba-ball-general-view-iso.jpg"),
            Picture("https://images.chosun.com/resizer/2yWtoWbz9NtkyycfFjj9IPeK2_M=/616x0/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/CVMXCHNQHVAFNIKFHN53IZX6DI.jpg"),
            Picture("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSwlKDBOdO_Kd8FTkfF5WWSh-OG_vu3R-QUIw&usqp=CAU"),
            Picture("https://jumpbcdn.iwinv.biz/news/data/20240212/p1065551806519021_653_h.jpg"),
            Picture("https://image-cdn.hypb.st/https%3A%2F%2Fkr.hypebeast.com%2Ffiles%2F2023%2F12%2FDetroit-Pistons-set-NBA-single-season-record-for-longest-losing-streak-01.jpg?cbr=1&q=90"),
            Picture("https://newsimg-hams.hankookilbo.com/2022/05/17/34dd23d5-d470-416a-9640-6362a8c2ce6d.jpg"),
            Picture("https://sothebys-com.brightspotcdn.com/98/77/152062e749c0b3a06bc0e692c6ca/2gettyimages-1246881311.jpg"),
            Picture("https://images.chosun.com/resizer/mTuouNGPjRpju06HbitVR6yuYBk=/616x0/smart/cloudfront-ap-northeast-1.images.arcpublishing.com/chosun/WFUUXZJFQJPLFD3IQJWGFX7TOM.jpg"),
            Picture("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjz9LNifgEvJSxsTAxXJ7VXhI8PpFmJ9_ldQ&usqp=CAU"),
            Picture("https://img.sbs.co.kr/newimg/news/20230623/201799134_500.jpg"),
            Picture("https://img.hankyung.com/photo/202312/PRU20231210268901009_P4.jpg")
        )
    }
}