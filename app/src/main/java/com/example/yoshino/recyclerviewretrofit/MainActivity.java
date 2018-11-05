package com.example.yoshino.recyclerviewretrofit;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yoshino.recyclerviewretrofit.model.User;
import com.example.yoshino.recyclerviewretrofit.remote.APIUtils;
import com.example.yoshino.recyclerviewretrofit.remote.UserService;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


User userUpdate;
Integer deleteId;
    private static final String TAG = "MyActivity";
    private RecyclerView myrecyclerView;
    List<User> list = new ArrayList<User>();
 User pruebUser;
//    RecyclerView.Adapter mAdapter;
    UserAdapter mAdapter;
    RecyclerView.LayoutManager mlayoutManager;
    EditText edName,edDate,edNick;

    UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myrecyclerView = findViewById(R.id.user_list);
        edName = findViewById(R.id.edNombre);
        edNick = findViewById(R.id.edNick);
        edDate = findViewById(R.id.edFecha);

        userService = APIUtils.getUserService();
        new TaskUserList().execute();



    }






    public void getUsersList(){
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                list = response.body();
                mAdapter = new UserAdapter(list,MainActivity.this);
                myrecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()  ));
                myrecyclerView.setAdapter(mAdapter);

                mAdapter.setOnItemClick(new UserAdapter.OnItemClickLister() {
                    @Override
                    public void onItemClick(int position) {
                       // list.get(position).getNick();
                        Toast.makeText(MainActivity.this, ""+list.get(position).getId(), Toast.LENGTH_SHORT).show();

                        showUpdateBoxItem(position);
                    }

//                    @Override
//                    public void onDeleteClick(final int position) {
//                      }

                });

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });
    }

    public String ObtenerName() {
        return edName.getText().toString();
    }

    public String ObtenerNick() {
        return edNick.getText().toString();
    }

    public String ObtenerDate() {
        return edDate.getText().toString();
    }

    public void newUser(View view) {

    if (ObtenerName().equals("")||ObtenerName().equals(" ")){
        Toast.makeText(this, "Escriba el nombre", Toast.LENGTH_SHORT).show();
    }
       else if (ObtenerNick().equals("")||ObtenerNick().equals(" ")){
            Toast.makeText(this, "Escriba una nick", Toast.LENGTH_SHORT).show();
        }

       else if (ObtenerDate().equals("")||ObtenerDate().equals(" ")){
            Toast.makeText(this, "Escriba una fecha", Toast.LENGTH_SHORT).show();
        }

        else {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Confirmar la operación");
        builder.setTitle("Mensaje...");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                new TaskNewUser().execute();
                new TaskUserList().execute();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

              }
    }



    public void addUser(User u){

        Call<User> call = userService.addUser(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this, "Usuario Creado", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });

    }




    public void deleteUser(Integer id){

        Call<User> call = userService.deleteUser(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this, "Usuario Elimado", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });

    }



    public void updateUser(User u){

        Call<User> call = userService.updateUser(u);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this, "Usuario Update", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("Error",t.getMessage());
            }
        });

    }




    public class TaskUpdateUser extends AsyncTask<Void,Void,Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, "","Cargando...");
        }

        @Override
        protected Void doInBackground(Void... Void) {



            updateUser(userUpdate);

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();



        }

    }




    public class TaskUserList extends AsyncTask<Void,Void,Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, "","Cargando...");
        }
        String    estado1;
        @Override
        protected Void doInBackground(Void... voids) {

            getUsersList();

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();



            }

        }


    public class TaskUserDelete extends AsyncTask<Void,Void,Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this, "","Cargando...");
        }
        String    estado1;
        @Override
        protected Void doInBackground(Void... voids) {

            deleteUser(deleteId);

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();



        }

    }




    public class TaskNewUser extends AsyncTask<Void,Void,Void> {

        ProgressDialog progressDialog;
        User u;
        @Override
        protected void onPreExecute() {
           u = new User(ObtenerName(),ObtenerDate(),ObtenerNick());
            progressDialog = ProgressDialog.show(MainActivity.this, "","Cargando...");
        }

        @Override
        protected Void doInBackground(Void... voids) {

            addUser(u);

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            edNick.setText("");
            edDate.setText("");
            edName.setText("");


        }

    }



    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Quieres Salir de la aplicación");
        builder.setTitle("Mensaje...");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public  void showUpdateBoxItem(final int index){
        final Dialog dialog = new Dialog(this);
        dialog.setTitle("Update User");
        dialog.setContentView(R.layout.update_box);


       final EditText edName = dialog.findViewById(R.id.upName);
       final EditText edDate = dialog.findViewById(R.id.upDate);
       final EditText edNick = dialog.findViewById(R.id.upNick);
       final TextView txtId = dialog.findViewById(R.id.txtId);





        edName.setText( list.get(index).getName());
        edDate.setText( list.get(index).getDate()+"");
        edNick.setText( list.get(index).getNick());
        txtId.setText( list.get(index).getId()+"");





        Button bt = dialog.findViewById(R.id.btdone);
        Button btD = dialog.findViewById(R.id.btdelete);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userUpdate = new User (Integer.parseInt( txtId.getText().toString()),
                        edName.getText().toString(),
                        edDate.getText().toString(),
                        edNick.getText().toString());

                new TaskUpdateUser().execute();
                new TaskUserList().execute();
                dialog.dismiss();
            }
        });



        dialog.show();


        btD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                AlertDialog.Builder builderD = new AlertDialog.Builder(MainActivity.this);
                builderD.setMessage("Confirmar la operación");
                builderD.setTitle("Mensaje...");
                builderD.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        deleteId = list.get(index).getId();
                        new TaskUserDelete().execute();
                        new TaskUserList().execute();
                        dialog.cancel();
                    }
                });

                builderD.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog dialogDelete = builderD.create();
                dialogDelete.show();

            }
        });

    }

}
