import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';
const TOKEN = 'token';

@Injectable({
  providedIn: 'root',
})
export class StorageService {
  constructor() {}

  static getEmail(): string {
    const token = this.getToken(); // Invoke the getToken method
    if (token) {
      const decodedToken: any = jwtDecode(token);
      return decodedToken.sub; // Assuming 'sub' contains the email in your token
    }
    return '';
  }

  static saveToken(token: string): void {
    (window as any).localStorage.removeItem(TOKEN);
    (window as any).localStorage.setItem(TOKEN, token);
  }

  static getToken(): string | null {
    return (window as any).localStorage.getItem(TOKEN);
  }

  static isLoggedIn(): boolean {
    if (this.getToken() === null) {
      return false;
    }
    return true;
  }

  static signout() {
    window.localStorage.removeItem(TOKEN);
  }
}
