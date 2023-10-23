import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';

export const LoadingInterceptorSkipHeader = 'X-Skip-Loading-Interceptor';

@Injectable({
  providedIn: 'root',
})
export class ApiService {

  private static readonly DATE_FORMAT = 'YYYY-MM-DD';

  protected API_URL = 'http://localhost:8000/';
  private headers: HttpHeaders = new HttpHeaders({
    'Accept': 'application/json',
    'Cache-Control': 'no-cache',
    'Pragma': 'no-cache',
    'Expires': '0',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
    'Access-Control-Allow-Headers': ' Origin, Content-Type, X-Auth-Token'
  });
  readonly RESPONSE_BLOB = 'blob';

  constructor(protected http: HttpClient) {
  }

  public get<T>(path: string, params?: any, ignoreLoadingInterceptor?: boolean, observeResponse = false): Observable<T> {
    const queryParams = this.serializeParams(params);
    const options = this.defineOptions(!!ignoreLoadingInterceptor, observeResponse);
    return this.http.get<T>(`${this.API_URL}${path}${queryParams}`, options);
  }

  public post<T>(path: string, data: any, params?: any, ignoreLoadingInterceptor?: boolean, observeResponse = false, responseType?: string, isFileUpload?: boolean): Observable<T> {
    const body = isFileUpload ? data : JSON.stringify(data);
    const queryParams = this.serializeParams(params);
    const options = this.defineOptions(!!ignoreLoadingInterceptor, observeResponse, undefined, responseType, isFileUpload);
    return this.http.post<T>(`${this.API_URL}${path}${queryParams}`, body, options);
  }

  public patch<T>(path: string, data: any, params?: any, ignoreLoadingInterceptor?: boolean, observeResponse = false, responseType?: string, isFileUpload?: boolean): Observable<T> {
    const body = isFileUpload ? data : JSON.stringify(data);
    const queryParams = this.serializeParams(params);
    const options = this.defineOptions(!!ignoreLoadingInterceptor, observeResponse, undefined, responseType, isFileUpload);
    return this.http.patch<T>(`${this.API_URL}${path}${queryParams}`, body, options);
  }

  public put<T>(path: string, data: any, params?: any, ignoreLoadingInterceptor?: boolean, observeResponse = false, responseType?: string, isFileUpload?: boolean): Observable<T> {
    const body = isFileUpload ? data : JSON.stringify(data);
    const queryParams = this.serializeParams(params);
    const options = this.defineOptions(!!ignoreLoadingInterceptor, observeResponse, undefined, responseType, isFileUpload);
    return this.http.put<T>(`${this.API_URL}${path}${queryParams}`, body, options);
  }

  public delete<T>(path: string, params?: any, ignoreLoadingInterceptor?: boolean): Observable<T> {
    const queryParams = this.serializeParams(params);
    const options = this.defineOptions(!!ignoreLoadingInterceptor);
    return this.http.delete<T>(`${this.API_URL}${path}${queryParams}`, options);
  }

  public serializeParams(params: any): string {
    if (params) {
      return '?' + Object.keys(params).map(function (key) {
        return key + '=' + encodeURIComponent(params[key]);
      }).join('&');
    }
    return '';
  }

  private defineOptions(ignoreLoadingInterceptor: boolean, observeResponse = false, customHeaders?: HttpHeaders, responseType?: string, isFileUpload?: boolean): { headers: any, observe?: any, responseType?: any } {
    let headers = ignoreLoadingInterceptor ? this.headers.set(LoadingInterceptorSkipHeader, ignoreLoadingInterceptor.toString()) : this.headers;
    if (!isFileUpload) {
      headers = headers.set('Content-Type', 'application/json');
    }

    if (customHeaders) {
      customHeaders.keys().forEach(nomeHeader => {
        headers = headers.set(nomeHeader, customHeaders.get(nomeHeader) ?? '');
      });
    }
    const options = {headers};
    this.setObserveResponse(observeResponse, options);
    this.setResponseType(responseType ?? '', options);
    return options;
  }

  private setResponseType(responseType: string, options: any): void {
    if (responseType) {
      options['responseType'] = responseType;
    }
  }

  private setObserveResponse(observeResponse: boolean, options: any): void {
    if (observeResponse) {
      options['observe'] = 'response';
    }
  }

}
