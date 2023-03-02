package com.estarly.petadoptionapp.data.repositories

import com.estarly.petadoptionapp.base.BaseResultRepository
import com.estarly.petadoptionapp.data.database.dao.UserDao
import com.estarly.petadoptionapp.data.database.entities.UserEntity
import com.estarly.petadoptionapp.domain.model.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userDao: UserDao
){
    suspend fun getUser(): BaseResultRepository<UserModel> {
        return try {
            val response = userDao.getUser()
            if(response!=null){
                BaseResultRepository.Success(response.toData())
            }else{
                BaseResultRepository.NullOrEmptyData
            }
        }catch (e : Exception){
            BaseResultRepository.Error(e)
        }
    }
    suspend fun insertUser(userModel: UserModel){ userDao.insertUser(userModel.toData()) }
}
fun UserModel.toData() : UserEntity =
    UserEntity(
        id, name,email
    )