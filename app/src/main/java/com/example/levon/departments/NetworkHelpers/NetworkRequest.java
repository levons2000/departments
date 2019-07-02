package com.example.levon.departments.NetworkHelpers;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.levon.departments.DepartmentPageActivity;
import com.example.levon.departments.DepartmentsActivity;
import com.example.levon.departments.EmployeeActivity;
import com.example.levon.departments.LoginActivity;
import com.example.levon.departments.LoginFragment;
import com.example.levon.departments.Models.Department;
import com.example.levon.departments.Models.Employee;
import com.example.levon.departments.Models.Job;
import com.example.levon.departments.Models.Task;
import com.example.levon.departments.Models.UserLoginModel;
import com.example.levon.departments.Models.UserModel;
import com.example.levon.departments.Models.UserTokenModel;
import com.example.levon.departments.R;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkRequest {
    private static NetworkRequest shared = null;
    public static final String BASE_URL = "https://immense-harbor-92209.herokuapp.com/api/";
    private String idToken;

    public static NetworkRequest sharedInstance() {
        if (shared == null) {
            shared = new NetworkRequest();
        }

        return shared;
    }

    private NetworkRequest(){}

    public void startRegister(final LoginActivity context, final UserModel userModel) {
        makeLoginRequest(context, userModel, new UserLoginModel("admin", true, "admin"));
    }

    public void makeRegisterRequest(final LoginActivity context, final UserModel userModel) {
        context.disableCurrentFragment();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + idToken)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
                baseUrl(BASE_URL).
                client(client).
                addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();

        UserRegistration userRegistration = retrofit.create(UserRegistration.class);
        Call<UserModel> call = userRegistration.registerAccount(userModel);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                context.enableCurrentFragment();
                if (response.code() == 200) {
                    Toast.makeText(context, context.
                            getResources().
                            getString(R.string.success_registration),
                            Toast.LENGTH_LONG).show();
                    context.getSupportFragmentManager().
                            beginTransaction().
                            replace(R.id.login_fragment_container, new LoginFragment()).
                            commit();
                } else {
                    Toast.makeText(context,
                            context.
                                    getResources().
                                    getString(R.string.fail_registration),
                            Toast.LENGTH_LONG).show();
                    Log.d("went wrong", String.valueOf(response.code()) + response.body());
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                context.enableCurrentFragment();
                Toast.makeText(context,
                        context.
                                getResources().
                                getString(R.string.fail_registration),
                        Toast.LENGTH_LONG).show();
                Log.e("RegistrationFailure", t.getLocalizedMessage(), t);
            }
        });
    }

    public void makeLoginRequest(final LoginActivity context,
                                 final UserLoginModel userLoginModel) {
        context.disableCurrentFragment();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();

        UserLogin userLogin = retrofit.create(UserLogin.class);
        Call<UserTokenModel> call = userLogin.loginToAccount(userLoginModel);
        call.enqueue(new Callback<UserTokenModel>() {
            @Override
            public void onResponse(Call<UserTokenModel> call, Response<UserTokenModel> response) {
                context.enableCurrentFragment();
                if (response.code() == 200) {
                    idToken = response.body().getIdToken();
                    context.startActivity(new Intent(context, DepartmentsActivity.class));
                } else {
                    Toast.makeText(context,
                            context.
                                    getResources().
                                    getString(R.string.bad_authentication),
                            Toast.LENGTH_LONG).show();
                    Log.d("LoginFailure", String.valueOf(response.code()) + response.body());
                }
                Log.d("requestbody", call.request().toString());
            }

            @Override
            public void onFailure(Call<UserTokenModel> call, Throwable t) {
                context.enableCurrentFragment();
                Toast.makeText(context,
                        context.
                                getResources().
                                getString(R.string.fail_registration),
                        Toast.LENGTH_LONG).show();
                Log.e("LoginFailure", t.getLocalizedMessage(), t);
                Log.d("requestbody", call.request().toString());
            }
        });
    }

    public void makeLoginRequest(final LoginActivity context, final UserModel userModel, final UserLoginModel userLoginModel) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();

        UserLogin userLogin = retrofit.create(UserLogin.class);
        Call<UserTokenModel> call = userLogin.loginToAccount(userLoginModel);
        call.enqueue(new Callback<UserTokenModel>() {
            @Override
            public void onResponse(Call<UserTokenModel> call, Response<UserTokenModel> response) {
                if (response.code() == 200) {
                    idToken = response.body().getIdToken();
                    makeRegisterRequest(context, userModel);
                } else {
                    Log.d("LoginFailure", String.valueOf(response.code()) + response.body());
                }
                Log.d("requestbody", call.request().toString());
            }

            @Override
            public void onFailure(Call<UserTokenModel> call, Throwable t) {
                Log.e("LoginFailure", t.getLocalizedMessage(), t);
                Log.d("requestbody", call.request().toString());
            }
        });
    }

    public void makeDepartmentsGetRequest(final DepartmentsActivity context) {
        final List<Department> list = new ArrayList<>();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + idToken)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
                client(client).
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = retrofitBuilder.build();

        DepartmentsGet departmentsGet = retrofit.create(DepartmentsGet.class);
        Call<List<Department>> call = departmentsGet.getDepartments();
        call.enqueue(new Callback<List<Department>>() {
            @Override
            public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {
                if (response.code() == 200) {
                    list.addAll(0, response.body());
                    context.setupDepartmentsList(list);
                } else {
                    Log.d("DepartmentFailure", response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Department>> call, Throwable t) {
                Log.e("DepartmentFailure", t.getLocalizedMessage(), t);
            }
        });
    }

    public void makeEmployeeGetRequest(final DepartmentPageActivity context) {
        final List<Employee> list = new ArrayList<>();
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + idToken)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
                baseUrl(BASE_URL).
                client(client).
                addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()));

        Retrofit retrofit = retrofitBuilder.build();

        EmployeesGet employeesGet = retrofit.create(EmployeesGet.class);
        Call<List<Employee>> call = employeesGet.getEmployees("15");
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.code() == 200) {
                    list.addAll(0, response.body());
                    context.addEmployeeList(list);
                } else {
                    Log.d("EmployeeFailure", response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.e("EmployeeFailure", t.getLocalizedMessage(), t);
            }
        });
    }

    public void makeJobGetRequest(final EmployeeActivity context) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + idToken)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
                baseUrl(BASE_URL).
                client(client).
                addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()));

        Retrofit retrofit = retrofitBuilder.build();

        JobGet jobsGet = retrofit.create(JobGet.class);
        Call<List<Job>> call = jobsGet.getJobs("15");
        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.code() == 200) {
                    makeTaskGetRequest(context, response.body());
                } else {
                    Log.d("JobFailure", response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.e("JobFailure", t.getLocalizedMessage(), t);
            }
        });
    }

    public void makeTaskGetRequest(final EmployeeActivity context, final List<Job> jobList) {
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + idToken)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
                baseUrl(BASE_URL).
                client(client).
                addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()));

        Retrofit retrofit = retrofitBuilder.build();

        TaskGet taskGet = retrofit.create(TaskGet.class);
        Call<List<Task>> call = taskGet.getTasks("15");
        call.enqueue(new Callback<List<Task>>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.code() == 200) {
                    context.setupRecyclerView(jobList, response.body());
                } else {
                    Log.d("TaskFailure", response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Log.e("TaskFailure", t.getLocalizedMessage(), t);
            }
        });
    }
}
