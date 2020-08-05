package com.core.data.network.interfaces

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
import com.core.data.model.rank.RankResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface ApisHelper {

    @GET("")
    fun getMatchesByDate(date: String): Deferred<Response<MatchesResponseModel>>

    @GET("")
    fun getMatchDetails(matchId: String): Deferred<Response<MatchDetailsResponseModel>>

    @POST("")
    fun sendMatchExpectation(matchExpectationRequest: MatchExpectationRequest): Deferred<Response<MatchExpectationResponse>>

    @GET("Leagues/GetLeagues/{lang}")
    fun getLeaguesList(@Path("lang") lang: String): Deferred<Response<LeaguesListResponseModel>>

    @GET("")
    fun getLeaguesMatchesByDate(
        date: String,
        leagueId: String
    ): Deferred<Response<MatchesResponseModel>>

    @GET("")
    fun getLeaguesRank(leagueId: String): Deferred<Response<LeaguesRankResponseModel>>

    @GET("")
    fun getRanks(type: String, selectedNum: Int): Deferred<Response<RankResponseModel>>

    @POST("Account/LoginWithFacebook")
    fun login(@Body loginRequest: LoginRequest): Deferred<Response<LoginResponse>>

    @GET("")
    fun getPrizes(): Deferred<Response<PrizesResponse>>

    @POST("")
    fun changePhone(changePhoneRequest: ChangePhoneRequest): Deferred<Response<ChangePhoneResponse>>
}
