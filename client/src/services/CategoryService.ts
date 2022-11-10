import axios from "axios";
import { BASE_URL } from "../api/url";
import { CategoryDto } from "../types/CategoryDto";


export class CategoryService {




    static async getAllCategories():Promise<CategoryDto[]|null> {
        try {
            const response = await axios.get(`${BASE_URL}/categories`)
            return response.data;
        } catch (error) {
            console.log(error);
            return null;
        }
    }
}