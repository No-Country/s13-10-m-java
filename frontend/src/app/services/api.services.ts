import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
@Injectable({
	providedIn: 'root'
})
export class ApiService {
	constructor(private _http: HttpClient) {}

	private POKE_API = 'https://pokeapi.co/api/v2/pokemon';
	private POKE_MOCK_POSTMAN = 'https://8be7b726-3361-4635-9173-7607c331f7a3.mock.pstmn.io/charizard';

	getDitto() {
		return this._http.get(`${this.POKE_API}/ditto`);
	}
	getCharizard() {
		return this._http.get(this.POKE_MOCK_POSTMAN);
	}
}