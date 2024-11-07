import axios, { AxiosResponse } from "axios";

const axiosApi = axios.create({
  baseURL: `/`,
  timeout: 1000,
  headers: { "Content-Type": "application/json" },
});

export default {
  products(): Promise<AxiosResponse<string>> {
    return axiosApi.get(`products`);
  },

  product(id: number): Promise<AxiosResponse<string>> {
    return axiosApi.get(`products/${id}`);
  },
};
