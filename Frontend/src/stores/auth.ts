import axios from 'axios'
import { defineStore } from 'pinia'

function getJsonByToken(token: string) {
  const data = token.split('.')[1];
  const decodedData = atob(data)
  const json = JSON.parse(decodedData)
  return json;
}

function setUserByJsonData(user, jsonData) {
  user.id = jsonData.id
  user.name = jsonData.name
  user.email = jsonData.email
}

export const useAuthStore = defineStore('auth', {
  state: () => ({
    user: {
      id: Number,
      name: undefined as string,
      email: undefined as string
    },
    token: String
  }),
  getters: {
    isAuthenticated: (state) => !!state.token
  },
  actions: {
    async login(email: string, password: string) {
      const requestData = {
        email: email,
        password: password,
      };

      let response = await axios.post("/api/authenticate/login", requestData)
      const token = response.data.token
      const jsonData = getJsonByToken(token)
      this.token = token
      setUserByJsonData(this.user, jsonData)
      localStorage.setItem("token", token);
      return response
    },

    logout() {
      this.token = null
      this.user.id = null
      this.user.name = null
      this.user.email = null
      localStorage.removeItem("token");
    },

    async initStates() {
      const token = localStorage.getItem("token")
      if(token){
        const jsonData = getJsonByToken(token)
        setUserByJsonData(this.user, jsonData)
        this.token = token
      }
      else {
        this.logout()
      }
    }
  }
})
