package com.core.data.network.interfaces

import com.core.data.model.extraPoints.ConfirmWatchingAdResponseModel
import com.core.data.model.help.HelpResponseModel
import com.core.data.model.leagues.LeaguesListResponseModel
import com.core.data.model.leaguesRank.LeaguesRankResponseModel
import com.core.data.model.login.LoginRequest
import com.core.data.model.login.LoginResponse
import com.core.data.model.matchDetails.MatchDetailsResponseModel
import com.core.data.model.matchDetails.MatchExpectationRequest
import com.core.data.model.matchDetails.MatchExpectationResponse
import com.core.data.model.matches.MatchesResponseModel
import com.core.data.model.prizes.PrizesResponse
import com.core.data.model.profile.ChangePhoneRequest
import com.core.data.model.profile.ChangePhoneResponse
import com.core.data.model.rank.RankByMonthResponseModel
import com.core.data.model.rank.RankResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ApisHelper {

    @GET("Home/GetAllMatch/{lang}/{userId}")
    fun getMatchesByDate(
        @Path("lang") lang: String,
        @Path("userId") userId: String,
        @Query("date") date: String
    ): Deferred<Response<MatchesResponseModel>>

    @GET("Home/GetMatch/{lang}/{matchId}/{userId}")
    fun getMatchDetails(
        @Path("lang") lang: String,
        @Path("matchId") matchId: String,
        @Path("userId") userId: String
    ): Deferred<Response<MatchDetailsResponseModel>>

    @POST("MatchesExpectations")
    fun sendMatchExpectation(@Body matchExpectationRequest: MatchExpectationRequest): Deferred<Response<MatchExpectationResponse>>

    @GET("Leagues/GetLeagues/{lang}")
    fun getLeaguesList(@Path("lang") lang: String): Deferred<Response<LeaguesListResponseModel>>

    @GET("Home/GetLeagueMatches/{lang}/{leagueId}/{userId}")
    fun getLeaguesMatchesByDate(
        @Path("lang") lang: String,
        @Path("leagueId") leagueId: String,
        @Path("userId") userId: String,
        @Query("date") date: String
    ): Deferred<Response<MatchesResponseModel>>

    @GET("")
    fun getLeaguesRank(leagueId: String): Deferred<Response<LeaguesRankResponseModel>>

    @GET("OrderByMonths/1")
    fun getRanks(type: String, selectedNum: Int): Deferred<Response<RankResponseModel>>

    @GET("OrderByMonths/{selectedNum}")
    fun getRanksByMonth(@Path("selectedNum") selectedNum: Int): Deferred<Response<List<RankByMonthResponseModel>>>

    @POST("Account/LoginWithFacebook")
    fun login(@Body loginRequest: LoginRequest): Deferred<Response<LoginResponse>>

    @GET("Awards/GetAwards/{lang}")
    fun getPrizes(@Path("lang") lang: String): Deferred<Response<PrizesResponse>>

    @POST("")
    fun changePhone(changePhoneRequest: ChangePhoneRequest): Deferred<Response<ChangePhoneResponse>>

    @GET("Helpers/GetHeplers/{lang}")
    fun getHelpsList(@Path("lang") lang: String): Deferred<Response<HelpResponseModel>>

    @GET("AdvertisementScores/checkAdvertisementAvilabal")
    fun checkRewardAdAvailability(@Query("userid") userid: String): Deferred<Response<Boolean>>

    @GET("AdvertisementScores/wachAdvertisement")
    fun confirmWatchingRewardAd(@Query("userId") userId: String): Deferred<Response<ConfirmWatchingAdResponseModel>>
}
